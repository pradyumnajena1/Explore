package repository.inmem;

public class RocksDbDriver {
  public static void main(String[] args) {
    KeyValueRepository<String, String> repository = new RocksDBRepositoryImpl();
    repository.save("key1", "value1");
    repository.save("key2", repository.find("key1"));
    repository.delete("key1");
    System.out.println(repository.find("key2"));
  }
}
