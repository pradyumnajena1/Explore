package LLD;

public class SingleTon {
  private static SingleTon instance  ;

  private static class SingleTonHolder{
      private static final SingleTon instance = new SingleTon();
  }

    private SingleTon(){
        // initialization
    }

    public static    SingleTon getInstance(){


       return SingleTonHolder.instance;

    }

}
