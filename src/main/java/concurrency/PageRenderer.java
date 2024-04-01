package concurrency;

import java.util.List;
import java.util.StringJoiner;
import java.util.concurrent.*;

public class PageRenderer {

    public static void main(String[] args) {
        PageRenderer renderer = new PageRenderer(Executors.newFixedThreadPool(3));
        renderer.renderPage("text");
    }

    private final ExecutorService executorService;

    public PageRenderer(ExecutorService executorService){
         this.executorService = executorService;
    }
    public void renderPage(CharSequence source)   {
        List<ImageInfo> info = scanForImageInfo(source);
        CompletionService<ImageData> completionService = new ExecutorCompletionService<ImageData>(executorService);
        for(ImageInfo imageInfo:info){
            completionService.submit(() -> imageInfo.downloadImage());
        }
        for(int i=0;i< info.size();i++){
            ImageData imageData = null;
            try {
                Future<ImageData> imageDataFuture = completionService.take();
                imageData = imageDataFuture.get();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } catch (ExecutionException e) {
                throw launderThrowable(e.getCause());
            }
            renderImage(imageData);
        }
    }
    public static RuntimeException launderThrowable(Throwable t) {
        if (t instanceof RuntimeException)
            return (RuntimeException) t;
        else if (t instanceof Error)
            throw (Error) t;
        else
            throw new IllegalStateException("Not unchecked", t);
    }

    private void renderImage(ImageData imageData) {
        System.out.println(imageData);
    }

    private List<ImageInfo> scanForImageInfo(CharSequence source) {
        return List.of(new ImageInfo("/hello.png"),new ImageInfo("/hello2.png"),new ImageInfo("/hello3.png"));
    }

    private static class ImageInfo {
        String imageUrl;

        public ImageInfo(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public ImageData downloadImage() {
            return new ImageData(imageUrl);
        }
    }

    private static class ImageData {
        String imageName;

        public ImageData(String imageName) {
            this.imageName = imageName;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", ImageData.class.getSimpleName() + "[", "]")
                    .add("imageName='" + imageName + "'")
                    .toString();
        }
    }
}
