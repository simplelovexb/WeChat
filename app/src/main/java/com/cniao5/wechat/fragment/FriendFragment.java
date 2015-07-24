package com.cniao5.wechat.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cniao5.wechat.R;
import com.cniao5.wechat.adapter.ContactAdapter;
import com.cniao5.wechat.model.Contact;
import com.cniao5.wechat.widget.SideBar;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import roboguice.inject.InjectView;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

public class FriendFragment extends Fragment {

    @InjectView(R.id.friend_stListView)
    private StickyListHeadersListView headersListView;

    private ContactAdapter mAdapter;
    private List<Contact> mContact;
    private SideBar sideBar;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view  = inflater.inflate(R.layout.layout_friend,container,false);
        sideBar = (SideBar)view.findViewById(R.id.sideBar);
        mAdapter = new ContactAdapter(getActivity(),mContact);
        headersListView = (StickyListHeadersListView)view.findViewById(R.id.friend_stListView);
        headersListView.setAdapter(mAdapter);
        return view;

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContact = new ArrayList<>();
        Contact contact1 = new Contact(new Long(1),1L,1L,"1","A","1","1","1",new Date(2015,3,15),new Date(2015,3,15),"ttt");
        Contact contact2 = new Contact(new Long(2),2L,2L,"2","B","2","2","2",new Date(2015,3,15),new Date(2015,3,15),"c");
        Contact contact3 = new Contact(new Long(3),3L,3L,"3","C","3","3","3",new Date(2014,4,23),new Date(2009,4,23),"ss");
        Contact contact4 = new Contact(new Long(4),1L,1L,"4","D","1","1","1",new Date(2015,3,15),new Date(2015,3,15),"fg");
        Contact contact5 = new Contact(new Long(5),2L,2L,"5","E","2","2","2",new Date(2015,3,15),new Date(2015,3,15),"fd");
        Contact contact6 = new Contact(new Long(6),3L,3L,"6","F","3","3","3",new Date(2014,4,23),new Date(2009,4,23),"dsd");
        Contact contact7 = new Contact(new Long(7),1L,1L,"7","G","1","1","1",new Date(2015,3,15),new Date(2015,3,15),"gd");
        Contact contact8 = new Contact(new Long(8),2L,2L,"8","H","2","2","2",new Date(2015,3,15),new Date(2015,3,15),"8");
        Contact contact9 = new Contact(new Long(9),3L,3L,"9","I","3","3","3",new Date(2014,4,23),new Date(2009,4,23),"9");
        mContact.add(contact2);
        mContact.add(contact1);
        mContact.add(contact3);
        mContact.add(contact4);
        mContact.add(contact5);
        mContact.add(contact6);
        mContact.add(contact7);
        mContact.add(contact8);
        mContact.add(contact9);
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sideBar.setOnTouchingLetterChangeListener(new SideBar.OnTouchingLetterChangeListener() {
            @Override
            public void onTouchingLetterChange(String s) {
                int position = mAdapter.getPositionForSection(s.charAt(0));
                headersListView.setSelection(position);
            }
        });
    }
}
