package repository.inmem;

import org.rocksdb.Options;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class RocksDBRepositoryImpl implements KeyValueRepository<String, String> {
  private static final Logger log = LoggerFactory.getLogger(RocksDBRepositoryImpl.class);
  private static final String NAME = "first-db";
  private File dbDir;
  private RocksDB db;

  public RocksDBRepositoryImpl() {
    initialize();
  }


  void initialize() {
    RocksDB.loadLibrary();
    final Options options = new Options();
    options.setCreateIfMissing(true);
    dbDir = new File("/tmp/rocks-db", NAME);
    try {
      Files.createDirectories(dbDir.getParentFile().toPath());
      Files.createDirectories(dbDir.getAbsoluteFile().toPath());
      db = RocksDB.open(options, dbDir.getAbsolutePath());
    } catch (IOException | RocksDBException ex) {
      log.error(
          "Error initializng RocksDB, check configurations and permissions, exception: {}, message: {}, stackTrace: {}",
          ex.getCause(),
          ex.getMessage(),
          ex.getStackTrace());
    }
    log.info("RocksDB initialized and ready to use");
  }

  public synchronized void save(String key, String value) {
    log.info("save");
    try {
      db.put(key.getBytes(), value.getBytes());
    } catch (RocksDBException e) {
      log.error(
          "Error saving entry in RocksDB, cause: {}, message: {}", e.getCause(), e.getMessage());
    }
  }

  public String find(String key) {
    log.info("find");
    String result = null;
    try {
      byte[] bytes = db.get(key.getBytes());
      if (bytes == null) return null;
      result = new String(bytes);
    } catch (RocksDBException e) {
      log.error(
          "Error retrieving the entry in RocksDB from key: {}, cause: {}, message: {}",
          key,
          e.getCause(),
          e.getMessage());
    }
    return result;
  }

  public void delete(String key) {
    log.info("delete");
    try {
      db.delete(key.getBytes());
    } catch (RocksDBException e) {
      log.error(
          "Error deleting entry in RocksDB, cause: {}, message: {}", e.getCause(), e.getMessage());
    }
  }
}
