package com.cniao5.wechat.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.cniao5.wechat.R;
import com.cniao5.wechat.model.Contact;

import java.util.List;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

;

/**
 * Created by acer on 2015/7/23.
 */
public class ContactAdapter extends BaseAdapter implements StickyListHeadersAdapter ,SectionIndexer{

    private Context mContext;
    private List<Contact> mContacts;

    private LayoutInflater mInflater;

    public ContactAdapter(Context context ,List<Contact> list){

        mContext = context;
        mContacts = list;
        mInflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return (mContacts==null)?0:mContacts.size();
    }

    @Override
    public Object getItem(int position) {
        return mContacts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mContacts.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHodler viewHodler;
        if (convertView == null){
            viewHodler = new ViewHodler();
            convertView = mInflater.inflate(R.layout.template_contact,parent,false);
            viewHodler.imageView = (ImageView)convertView.findViewById(R.id.imageviewHead);
            viewHodler.textView = (TextView)convertView.findViewById(R.id.txtName);
            convertView.setTag(viewHodler);
        }else{
            viewHodler = (ViewHodler) convertView.getTag();
        }

        viewHodler.textView.setText(mContacts.get(position).getName());
        viewHodler.imageView.setImageResource(R.drawable.default_head);

        return convertView;

    }
    class ViewHodler{
        ImageView imageView;
        TextView textView;
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {

        HeadViewHolder viewHodler = null;
        if (convertView == null){
            viewHodler = new HeadViewHolder();
            convertView = mInflater.inflate(R.layout.template_text,parent,false);
            viewHodler.textView = (TextView)convertView.findViewById(R.id.txtHeader);
            convertView.setTag(viewHodler);
        }else{
            viewHodler = (HeadViewHolder) convertView.getTag();
        }

        String pinyin = ""+mContacts.get(position).getPinyin().subSequence(0,1).charAt(0);
        viewHodler.textView.setText(pinyin);

        return convertView;
    }

    class HeadViewHolder{
        TextView textView;
    }


    @Override
    public long getHeaderId(int position) {
        return mContacts.get(position).getPinyin().subSequence(0, 1).charAt(0);
    }




    @Override
    public Object[] getSections() {
        return null;
    }

    @Override
    public int getPositionForSection(int sectionIndex) {
        if (sectionIndex == '#'){
            return 0;
        }
        for (int i = 0; i<getCount();i++){
            String sorStr = mContacts.get(i).getPinyin();
            char firstChar = sorStr.toUpperCase().charAt(0);
            if (firstChar == sectionIndex){
                return i;
            }
        }
        return -1;
    }

    @Override
    public int getSectionForPosition(int position) {
        return mContacts.get(position).getPinyin().subSequence(0,1).charAt(0);
    }
}
