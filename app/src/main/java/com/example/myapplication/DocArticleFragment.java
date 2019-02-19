package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.util.ArrayList;

public class DocArticleFragment extends Fragment {
    private ListView listview1;
    ArrayList<String> titles;
    ArrayList<String> links;

    @Nullable
        //@Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
           View v = inflater.inflate(R.layout.doc_fragment_article, null);
                listview1 = (ListView) v.findViewById(R.id.lvRss);

                getActivity().setContentView(R.layout.doc_fragment_article);

                titles = new ArrayList<String>();
                links = new ArrayList<String>();

                listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Uri uri = Uri.parse(links.get(position));
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);

                    }
                });

                new ProcessInBackground().execute();
                return v;
        }




        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

        }

        public InputStream getInputStream(URL url) {
            try {
                return url.openConnection().getInputStream();
            } catch (IOException e) {
                return null;
            }
        }

        public class ProcessInBackground extends AsyncTask<Integer, Void, Exception> {

            ProgressDialog progressDialog = new ProgressDialog(getActivity());

            Exception exception = null;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog.setMessage("Busy loading Rss...wait");
                progressDialog.show();
            }

            @Override
            protected Exception doInBackground(Integer... integers) {

                try {
                    URL url = new URL("http://www.cardiobrief.org/feed");

                    XmlPullParserFactory factory = XmlPullParserFactory.newInstance();

                    factory.setNamespaceAware(false);

                    XmlPullParser xpp = factory.newPullParser();

                    xpp.setInput(getInputStream(url), "UTF_8");

                    boolean insideItem = false;

                    int eventType = xpp.getEventType();

                    while (eventType != XmlPullParser.END_DOCUMENT) {
                        if (eventType == XmlPullParser.START_TAG) {
                            if (xpp.getName().equalsIgnoreCase("item")) {
                                insideItem = true;
                            } else if (xpp.getName().equalsIgnoreCase("title")) {
                                if (insideItem) {
                                    titles.add(xpp.nextText());
                                }
                            } else if (xpp.getName().equalsIgnoreCase("link")) {
                                if (insideItem) {
                                    links.add(xpp.nextText());
                                }
                            }
                        } else if (eventType == XmlPullParser.END_TAG && xpp.getName().equalsIgnoreCase("item")) {
                            insideItem = false;
                        }

                        eventType = xpp.next();

                    }


                } catch (MalformedURLException e) {
                    exception = e;
                } catch (XmlPullParserException e) {
                    exception = e;
                } catch (IOException e) {
                    exception = e;
                }

                return exception;
            }

            @Override
            protected void onPostExecute(Exception s) {
                super.onPostExecute(s);

                ArrayAdapter<String> adapter;
                adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, titles);
                listview1.setAdapter(adapter);

                progressDialog.dismiss();
            }
        }
    }

    //@Override
    /*public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.ButtonDoc2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"You are inside Doc Article Fragment", Toast.LENGTH_SHORT).show();
            }
        });
    }
}*/


