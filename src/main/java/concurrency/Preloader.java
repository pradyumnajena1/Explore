package concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Preloader {
    public static void main(String[] args) throws InterruptedException, DataloadException {
        Preloader preloader = new Preloader();
        preloader.init();
        ProductInfo productInfo = preloader.get();
        System.out.println(productInfo.productId);
    }
    private final FutureTask<ProductInfo> future = new FutureTask<>(new Callable<ProductInfo>() {
        @Override
        public ProductInfo call() throws DataloadException {
            return loadProductInfo();
        }
    });
    Thread thread = new Thread(future);

    public void init() {
        thread.start();
    }

    public ProductInfo get() throws InterruptedException, DataloadException {
        ProductInfo productInfo = null;
        try {
            productInfo = future.get();
            return productInfo;
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            if (cause instanceof DataloadException)
                throw (DataloadException) cause;
            else
                throw launderThrowable(cause);

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

    private ProductInfo loadProductInfo() throws DataloadException{
        return new ProductInfo("productId 1");
    }

    public static class ProductInfo {
        private String productId;

        public ProductInfo(String productId) {
            this.productId = productId;
        }
    }

    public static class DataloadException extends Exception {

    }
}
