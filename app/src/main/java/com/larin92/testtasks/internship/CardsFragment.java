package com.larin92.testtasks.internship;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.larin92.testtasks.internship.adapters.CardsListAdapter;
import com.larin92.testtasks.internship.adapters.CardsRecyclerAdapter;
import com.melnykov.fab.FloatingActionButton;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class CardsFragment extends Fragment {

    private static final String TAG = "CardsFragment";
    private static final String ARG_TAB_NUMBER = "tab-number";
    private int mTab;
    private CardsRecyclerAdapter mRecyclerAdapter;
    private CardsListAdapter mCardsListAdapter;
    private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CardsFragment() {
    }

    public static CardsFragment newInstance(int tabNumber) {
        CardsFragment fragment = new CardsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_TAB_NUMBER, tabNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTab = getArguments().getInt(ARG_TAB_NUMBER);
        switch (mTab) {
            case 0:
                mRecyclerAdapter = new CardsRecyclerAdapter(getActivity(), App.getInWork(), mListener);
                break;
            case 1:
                mRecyclerAdapter = new CardsRecyclerAdapter(getActivity(), App.getDone(), mListener);
                break;
            case 2:
                mCardsListAdapter = new CardsListAdapter(getActivity(), App.getWaiting(), mListener);
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cards_list, container, false);
        // Set the adapter
        FloatingActionButton mFab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        Context context = view.getContext();
        mTab = getArguments().getInt(ARG_TAB_NUMBER);
        if (mTab == 2) {
            ListView listView = (ListView) view.findViewById(R.id.listview);
            listView.setAdapter(mCardsListAdapter);
            mFab.attachToListView(listView);
            Log.v(TAG, "onCreateView CARDS LIST " + mTab);
        } else {
            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_cards);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(mRecyclerAdapter);
            mFab.attachToRecyclerView(recyclerView);
            Log.v(TAG, "onCreateView CARDS RECYCLER " + mTab);
        }
        //recyclerView.setAdapter(new MyCardRecyclerViewAdapter(DummyContent.ITEMS, mListener));
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        //Update argument type and name
        void onListFragmentInteraction(CardModel item);
    }
}
