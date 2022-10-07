public class PCSystem {
    private String assetID, modelName, LCDName;
    private int RAMSizeMB, diskSizeGB;
    public boolean GPUAvailable;

    public PCSystem(String assetID, String modelName, String LCDName, int RAMSizeMB, int diskSizeGB, boolean GPUAvailable){
        this.assetID=assetID;
        this.modelName=modelName;
        this.LCDName=LCDName;
        this.RAMSizeMB=RAMSizeMB;
        this.diskSizeGB=diskSizeGB;
        this.GPUAvailable=GPUAvailable;
    }
    public PCSystem(PCSystem p){
        this.assetID=p.assetID;
        this.modelName=p.modelName;
        this.LCDName=p.LCDName;
        this.RAMSizeMB=p.RAMSizeMB;
        this.diskSizeGB=p.diskSizeGB;
        this.GPUAvailable=p.GPUAvailable;
    }

    @Override
    public String toString(){
      return String.format("\n......................................\n" +
                      "%-15s, %-10s, %-10s\nRAM: %5d MB\t\tDisk: %5d GB\nGPU: %s",
              assetID, modelName, LCDName, RAMSizeMB, diskSizeGB, (GPUAvailable==true)? "Available":"Not Available");
    }

    @Override
    public boolean equals(Object o){
        PCSystem tmp = (PCSystem) o;

        return this.assetID.equals(tmp.assetID);
    }

    @Override
    public Object clone(){
        return new PCSystem(this);
    }
}
