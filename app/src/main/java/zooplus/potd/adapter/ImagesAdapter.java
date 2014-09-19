package zooplus.potd.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

import lombok.Getter;


public class ImagesAdapter extends BaseAdapter {

    @Getter
    private Context mContext;
    //
    ArrayList<ImageView> itemList = new ArrayList<>();

    public ImagesAdapter(Context c) {
        mContext = c;
    }

    public void add(ImageView imageView) {
        itemList.add(imageView);
    }

    public void clear() {
        itemList.clear();
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return itemList.get(position);
    }


}
