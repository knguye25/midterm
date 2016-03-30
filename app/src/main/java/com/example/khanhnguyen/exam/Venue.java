package com.example.khanhnguyen.exam;

import android.os.Parcel;
import android.os.Parcelable;

public class Venue implements Parcelable{
    private String id, venueName, categoryName, iconUrl;
    private int checkinCounts;

    public Venue(){this.checkinCounts = -1;}

    public String getId() {return id;}
    public void setId(String id) {this.id = id;}

    public String getVenueName() {return venueName;}
    public void setVenueName(String venueName) {this.venueName = venueName;}

    public String getCategoryName() {return categoryName;}
    public void setCategoryName(String categoryName) {this.categoryName = categoryName;}

    public String getIconUrl() {return iconUrl;}
    public void setIconUrl(String iconUrl) {this.iconUrl = iconUrl;}

    public int getCheckinCounts() {return checkinCounts;}
    public void setCheckinCounts(int checkinCounts) {this.checkinCounts = checkinCounts;}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(venueName);
        dest.writeString(categoryName);
        dest.writeString(iconUrl);
        dest.writeInt(checkinCounts);
    }

    protected Venue(Parcel in) {
        id = in.readString();
        venueName = in.readString();
        categoryName = in.readString();
        iconUrl = in.readString();
        checkinCounts = in.readInt();
    }

    public static final Creator<Venue> CREATOR = new Creator<Venue>() {
        @Override
        public Venue createFromParcel(Parcel in) {
            return new Venue(in);
        }

        @Override
        public Venue[] newArray(int size) {
            return new Venue[size];
        }
    };

    public boolean isComplete(){
        if(id!=null && venueName!=null && categoryName!=null && iconUrl!=null && checkinCounts!=-1)
            return (!id.isEmpty() && !venueName.isEmpty() && !categoryName.isEmpty() && !iconUrl.isEmpty());
        return false;
    }
}
