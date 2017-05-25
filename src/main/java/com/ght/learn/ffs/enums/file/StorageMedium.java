package com.ght.learn.ffs.enums.file;

public enum StorageMedium {
    LocalDisk, 
    MongoDBDocument, 
    MongoDBGridFS;
    
    public boolean isMe(StorageMedium storageMedium) {
	return this.equals(storageMedium);
    }
}
