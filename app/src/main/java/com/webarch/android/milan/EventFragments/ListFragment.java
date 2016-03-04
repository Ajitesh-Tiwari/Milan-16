package com.webarch.android.milan.EventFragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.webarch.android.milan.Activities.EventDetailActivity;
import com.webarch.android.milan.EventAdapter;
import com.webarch.android.milan.HelperObjects.Event;
import com.webarch.android.milan.R;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by ajitesh on 13/2/16.
 */
public class ListFragment extends Fragment {
    View view;
    private RecyclerView mRecyclerView;
    private EventAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<Event> myDataset;
    NodeList listEvents;
    int domain;
    String[] domains={"Dance","Aeromodelling","Music","Gaming","Literary","Drama","Arts","Environment","Fun-Zone","Online","Sports","Special","Fashion"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.list_fragment, container, false);
        domain= getArguments().getInt("Domain");
        myDataset=new ArrayList<>();
        mRecyclerView = (RecyclerView) view.findViewById(R.id.eventListView);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        // specify an adapter (see also next example)
        mAdapter = new EventAdapter(myDataset,getActivity());
        mRecyclerView.setAdapter(mAdapter);
        getDataset(domain);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAdapter.SetOnItemClickListener(new EventAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View v, int position) {

                Event event=myDataset.get(position);
                Node node=listEvents.item(position);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    NodeList rulesList=((Element) eElement.getElementsByTagName("rules").item(0)).getElementsByTagName("ul");
                    if(rulesList.getLength()>0){
                        rulesList=((Element)rulesList.item(0)).getElementsByTagName("li");
                        String [] rules=new String[rulesList.getLength()];
                        for(int i=0;i<rulesList.getLength();i++){
                            rules[i]=rulesList.item(i).getTextContent();
                            //Toast.makeText(getActivity(),rules[i], Toast.LENGTH_SHORT).show();
                        }
                        event.setRules(rules);
                    }
                    NodeList notesList=((Element) eElement.getElementsByTagName("notes").item(0)).getElementsByTagName("ul");
                    if(notesList.getLength()>0){
                        notesList=((Element)notesList.item(0)).getElementsByTagName("li");
                        String [] notes=new String[notesList.getLength()];
                        for(int i=0;i<notesList.getLength();i++){
                            notes[i]=notesList.item(i).getTextContent();
                        }
                        event.setNotes(notes);
                    }
                    NodeList coordinatorsList=((Element) eElement.getElementsByTagName("coordinators").item(0)).getElementsByTagName("ul");
                    if(coordinatorsList.getLength()>0){
                        coordinatorsList=((Element)coordinatorsList.item(0)).getElementsByTagName("li");
                        String [] coordinators=new String[coordinatorsList.getLength()];
                        for(int i=0;i<coordinatorsList.getLength();i++){
                            coordinators[i]=coordinatorsList.item(i).getTextContent();
                        }
                        event.setCoordinators(coordinators);
                    }
                }
                Intent intent=new Intent(getActivity(), EventDetailActivity.class);
                intent.putExtra("event",event);
                startActivity(intent);
            }
        });
    }

    public void getDataset(int position) {
            try {
                InputStream is = getActivity().getAssets().open("DataEvents.xml");

                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(is);

                Element element=doc.getDocumentElement();
                element.normalize();
                Element domain= (Element) element.getElementsByTagName(domains[position].toLowerCase()).item(0);
                listEvents=domain.getElementsByTagName("event");

                for(int i=0;i<listEvents.getLength();i++)
                {
                    Node node=listEvents.item(i);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElement = (Element) node;
                        Event event=new Event(eElement.getElementsByTagName("name").item(0).getTextContent(),domains[position]);
                        switch (this.domain){
                            case 3:
                                try {
                                    NodeList introList = ((Element) eElement.getElementsByTagName("intro").item(0)).getElementsByTagName("ul");
                                    if (introList.getLength() > 0) {
                                        introList = ((Element) introList.item(0)).getElementsByTagName("li");
                                        String s = "";
                                        for (int j = 0; j < introList.getLength(); j++) {
                                            s = s + introList.item(j).getTextContent() + "\n\n";
                                        }
                                        event.setIntro(s);
                                    }
                                }
                                catch (Exception e){
                                    Log.d("TAG",e.toString());
                                }
                                break;
                            case 4:
                                try {
                                    NodeList introList = ((Element) eElement.getElementsByTagName("intro").item(0)).getElementsByTagName("ul");
                                    if (introList.getLength() > 0) {
                                        introList = ((Element) introList.item(0)).getElementsByTagName("li");
                                        String s = "";
                                        for (int j = 0; j < introList.getLength(); j++) {
                                            s = s + introList.item(j).getTextContent() + "\n\n";
                                        }
                                        event.setIntro(s);
                                    }
                                }
                                catch (Exception e) {
                                    Log.d("TAG", e.toString());
                                }
                                break;
                            case 6:
                                try {
                                    NodeList introList = ((Element) eElement.getElementsByTagName("intro").item(0)).getElementsByTagName("ul");
                                    if (introList.getLength() > 0) {
                                        introList = ((Element) introList.item(0)).getElementsByTagName("li");
                                        String s = "";
                                        for (int j = 0; j < introList.getLength(); j++) {
                                            s = s + introList.item(j).getTextContent() + "\n\n";
                                        }
                                        event.setIntro(s);
                                    }
                                }
                                catch (Exception e) {
                                    Log.d("TAG", e.toString());
                                }
                                break;
                            default:
                                event.setIntro(eElement.getElementsByTagName("intro").item(0).getTextContent());
                        }
                        myDataset.add(event);
                    }
                }
            }
            catch (Exception e) {e.printStackTrace();
                Log.d("TAG",e.toString());}
            mAdapter.notifyDataSetChanged();
    }
}
