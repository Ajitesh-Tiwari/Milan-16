package com.webarch.android.milan.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.webarch.android.milan.HelperObjects.Event;
import com.webarch.android.milan.R;

public class EventDetailActivity extends AppCompatActivity {

    TextView tvIntro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        final Event event= (Event) getIntent().getSerializableExtra("event");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(event.getName());
        bindViews();
        tvIntro.setText(event.getIntro());
        if(event.getRules()==null)
            findViewById(R.id.cardRules).setVisibility(View.GONE);
        else{
            String[] rules=event.getRules();
            LinearLayout linearLayoutRules= (LinearLayout) findViewById(R.id.layoutRules);
            for(int i=0;i<rules.length;i++)
            {
                TextView textView=new TextView(this);
                textView.setText((i+1)+" - "+rules[i]);
                textView.setPadding(0, 0, 0, 25);
                linearLayoutRules.addView(textView);
            }
        }
        if(event.getNotes()==null)
            findViewById(R.id.cardNotes).setVisibility(View.GONE);
        else{
            String[] notes=event.getNotes();
            LinearLayout linearLayoutNotes= (LinearLayout) findViewById(R.id.layoutNotes);
            for(int i=0;i<notes.length;i++)
            {
                TextView textView=new TextView(this);
                textView.setText((i+1)+" - "+notes[i]);
                textView.setPadding(0,0,0,25);
                linearLayoutNotes.addView(textView);
            }

        }
        if(event.getCoordinators()==null)
            findViewById(R.id.cardCoordinators).setVisibility(View.GONE);
        else{
            String[] coordinators=event.getCoordinators();
            LinearLayout linearLayoutCoordinators= (LinearLayout) findViewById(R.id.layoutCoordinators);
            for(int i=0;i<coordinators.length;i++)
            {
                TextView textView=new TextView(this);
                textView.setText((i+1)+" - "+coordinators[i]);
                textView.setPadding(0,0,0,25);
                linearLayoutCoordinators.addView(textView);
            }

        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Milan '16\nEvent-"+event.getName()+"\nDomain-"+event.getDomain()+"\n"+event.getIntro()+"\nAvailable on Play Store-\nhttp://tinyurl.com/Webarch-Milan16";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, event.getName());
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share Event"));
            }
        });
    }
    public void bindViews(){
        tvIntro=(TextView)findViewById(R.id.tvIntro);
    }
}
