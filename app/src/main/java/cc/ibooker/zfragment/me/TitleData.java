package cc.ibooker.zfragment.me;

/**
 * 自定义数据带有HashCode
 * create by 邹峰立 on 2016/9/22
 */
public class TitleData {
    /**
     * classidid
     */
    public Integer classid;
    /**
     * 标题
     */
    public String arname;

    public Integer getId() {
        return classid;
    }

    public void setId(Integer id) {
        this.classid = id;
    }

    /**
     * @return the arname
     */
    public String getArname() {
        return arname;
    }

    /**
     * @param arname the arname to set
     */
    public void setArname(String arname) {
        this.arname = arname;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((arname == null) ? 0 : arname.hashCode());
        result = prime * result + ((classid == null) ? 0 : classid.hashCode());
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TitleData other = (TitleData) obj;
        if (arname == null) {
            if (other.arname != null)
                return false;
        } else if (!arname.equals(other.arname))
            return false;
        if (classid == null) {
            if (other.classid != null)
                return false;
        } else if (!classid.equals(other.classid))
            return false;
        return true;
    }

}
