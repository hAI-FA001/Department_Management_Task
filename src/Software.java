import java.lang.reflect.Field;

@SuppressWarnings("unused")
public class Software {
    private String name, ver, vendor, type;
    private Software[] requiredSoftware;
    private float  installationSizeMB;

    public Software(String name, String ver, String vendor, String type, Software[] requiredSoftware, float installationSize) {
        this.name = name;
        this.ver = ver;
        this.vendor = vendor;
        this.type = type;
        this.requiredSoftware = requiredSoftware;
        this.installationSizeMB = installationSize;
    }

    public Software(String name, String ver){
        this.name = name;
        this.ver = ver;

        vendor = "";
        type = "";
        requiredSoftware = null;
        installationSizeMB = 0;
    }

    public Software(Software s){
        this.name = s.name;
        this.ver = s.ver;
        this.vendor = s.vendor;
        this.type = s.type;
        this.installationSizeMB = s.installationSizeMB;

        if(s.requiredSoftware != null)
            {
                this.requiredSoftware = new Software[s.requiredSoftware.length];

                for (int i = 0; i < s.requiredSoftware.length; i++)
                    if (s.requiredSoftware[i] != null)
                        this.requiredSoftware[i] = (Software) s.requiredSoftware[i].clone();
            }
        else
            this.requiredSoftware = null;
    }

    public Software(){
        this.name = "";
        this.ver = "";
        this.vendor = "";
        this.type = "";
        this.installationSizeMB = 0;
        this.requiredSoftware = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Software[] getRequiredSoftware() {
        return requiredSoftware;
    }

    public void setRequiredSoftware(Software[] requiredSoftware) {
        this.requiredSoftware = requiredSoftware;
    }

    public float getInstallationSizeMB() {
        return installationSizeMB;
    }

    public void setInstallationSizeMB(float installationSizeMB) {
        this.installationSizeMB = installationSizeMB;
    }

    @Override
    public boolean equals(Object o){
        if(o==null || o.getClass() != Software.class)
            return false;

        Software tmp = (Software) o;

        return this.name.equals(tmp.name);
    }

    @Override
    public Object clone(){
        return new Software(this);
    }

    @Override
    public String toString(){
        StringBuilder out = new StringBuilder(String.format(
                        "``````````````````````" +
                        "\n%-20s" +
                        "\t\tVersion: %-7s" +
                        ((type.equals(""))? "" : "\nType: %-15s") +
                        ((vendor.equals(""))? "" : "\t\tVendor: %-15s") +
                        ((installationSizeMB == 0)? "": "\nInstallation Size: %10.2f MB"),

                name, ver, type, vendor, installationSizeMB
        ));

        if(requiredSoftware != null && requiredSoftware.length > 0) {
            out.append("\nRequired Softwares Info:");
            for (Software s : requiredSoftware)
                out.append("\n~~~~~~~").append(s.toString()
                        .replace("``````````````````````", "")
                        .replace("\n", "\n\t>"));
        }

        return out.toString();
    }

    public void addRequiredSoftware(Software s){
        if(this.requiredSoftware == null){
            this.requiredSoftware = new Software[]{s};
        }
        else{
            Software[] reqSwList = new Software[this.requiredSoftware.length+1];

            for(int i=0; i < this.requiredSoftware.length; i++)
                reqSwList[i] = (Software) this.requiredSoftware[i].clone();

            reqSwList[this.requiredSoftware.length] = s;

            this.requiredSoftware = reqSwList;
        }
    }

    public String csvFormat(boolean addFieldHeaders, boolean isReqSoftware){
        StringBuilder out = new StringBuilder();

        if(isReqSoftware){
            if(addFieldHeaders)
                out.append("name,ver,required by\n");

            out.append(getName().replace(",", " ")).append(",")
                    .append(getVer().replace(",", " "));

            return out.toString();
        }

        if(addFieldHeaders)
        {
            Field[] fields = Software.class.getDeclaredFields();

            for (Field f : fields)
                if(!f.getName().contains("req"))
                    out.append(f.getName()).append(',');
            out.append("lab").append("\n");
        }

        out.append(getName().replace(",", " ")).append(",")
                .append(getVer().replace(",", " ")).append(",")
                .append(getVendor().replace(",", " ")).append(",")
                .append(getType().replace(",", " ")).append(",").append(getInstallationSizeMB());


        return out.toString();
    }
}
