package amada.ramsatna.util.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.SectionIndexer;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import amada.ramsatna.R;
import amada.ramsatna.model.WordModel;

/**
 * Created by Hamza on 18/04/2016.
 */
public class DictionaryAdaptor extends BaseAdapter implements SectionIndexer{

    private final Context context;
    private ArrayList<WordModel> originalData;
    private ArrayList<WordModel> wordListOrig;
    private Map<String, Integer> mapIndex;
    private String[] sections;
    private ArrayList<String> tempw = new ArrayList<>();



    public DictionaryAdaptor(ArrayList<WordModel> words, Context context) {

        this.context = context;
        this.originalData = words;
        this.wordListOrig = new ArrayList<>(originalData);

        mapIndex = new LinkedHashMap<>();

        for (WordModel wrd : words) {
           tempw.add(wrd.getWord());
        }
        String[] w = tempw.toArray(new String[tempw.size()]);

        for (int i = 0; i < w.length; i++) {
            String word = w[i].trim();
            String index = word.substring(0, 1);
            if (mapIndex.get(index) == null)
                mapIndex.put(index, i);
        }

        Set<String> sectionLetters = mapIndex.keySet();
        ArrayList<String> sectionList = new ArrayList<>(sectionLetters);
        Collections.sort(sectionList);
        sections = new String[sectionList.size()];
        for (int i = 0; i < sectionList.size(); i++)
            sections[i] = sectionList.get(i);



    }


    @Override
    public int getCount() {
        return originalData.size();
    }

    @Override
    public Object getItem(int position) {
        return originalData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return originalData.get(position).hashCode();
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row, parent, false);

        TextView word = (TextView) rowView.findViewById(R.id.word);
        TextView meaning = (TextView) rowView.findViewById(R.id.meaning);

        word.setText(originalData.get(position).getWord());
        meaning.setText(originalData.get(position).getMeaning());

        return rowView;

    }


    public void filter(String text) {
        text = text.toLowerCase(Locale.getDefault());
        originalData.clear();

        if (text.length() == 0) {
            originalData.addAll(wordListOrig);
        } else {
            for (WordModel wp : wordListOrig) {
                if (wp.getSearch_word().toLowerCase().contains(text)) {
                    originalData.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public Object[] getSections() {
        return sections;
    }

    @Override
    public int getPositionForSection(int sectionIndex) {
        return mapIndex.get(sections[sectionIndex]);
    }

    @Override
    public int getSectionForPosition(int position) {
        return 1;
    }
}

