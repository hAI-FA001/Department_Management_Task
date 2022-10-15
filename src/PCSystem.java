import java.lang.reflect.Field;

@SuppressWarnings("unused")
public class PCSystem {
    private String assetID, modelName, LCDName;
    private int RAMSizeMB, diskSizeGB;
    private  boolean GPUAvailable;

    public PCSystem(){
        assetID = "";
        modelName = "";
        LCDName = "";
    }

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
      return String.format("""
                      
                      ......................................
                      %-15s, %-10s, %-10s
                      RAM: %5d MB\t\tDisk: %5d GB
                      GPU: %s
                      """,
              assetID, modelName, LCDName, RAMSizeMB, diskSizeGB, (GPUAvailable)? "Available":"Not Available");
    }

    @Override
    public boolean equals(Object o){
        if(o == null || o.getClass() != PCSystem.class)
            return false;

        PCSystem tmp = (PCSystem) o;

        return this.assetID.equals(tmp.assetID);
    }

    @Override
    public Object clone(){
        return new PCSystem(this);
    }

    public String getAssetID() {
        return assetID;
    }

    public void setAssetID(String assetID) {
        this.assetID = assetID;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getLCDName() {
        return LCDName;
    }

    public void setLCDName(String LCDName) {
        this.LCDName = LCDName;
    }

    public int getRAMSizeMB() {
        return RAMSizeMB;
    }

    public void setRAMSizeMB(int RAMSizeMB) {
        this.RAMSizeMB = RAMSizeMB;
    }

    public int getDiskSizeGB() {
        return diskSizeGB;
    }

    public void setDiskSizeGB(int diskSizeGB) {
        this.diskSizeGB = diskSizeGB;
    }

    public boolean isGPUAvailable() {
        return GPUAvailable;
    }

    public void setGPUAvailable(boolean GPUAvailable) {
        this.GPUAvailable = GPUAvailable;
    }

    public String csvFormat(boolean addFieldHeaders){
        StringBuilder out = new StringBuilder();

        if(addFieldHeaders)
        {
            Field[] fields = PCSystem.class.getDeclaredFields();

            for (Field f : fields)
                out.append(f.getName()).append(',');
            out.append("in lab").append("\n");
        }

        out.append(getAssetID().replace(",", " ")).append(",")
                .append(getModelName().replace(",", " ")).append(",")
                .append(getLCDName().replace(",", " ")).append(",")
                .append(getRAMSizeMB()).append(",").append(getDiskSizeGB()).append(",").
                append((isGPUAvailable())? "Available" : "Not Available");


        return out.toString();
    }

}
