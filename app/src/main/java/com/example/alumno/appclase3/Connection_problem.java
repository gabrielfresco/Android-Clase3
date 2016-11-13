package com.example.alumno.appclase3;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Connection_problem extends DialogFragment {


    public Connection_problem() {
        // Required empty public constructor
    }

    private static final String ARG_PARAM1 = "error";

    private Boolean condicion;
    private ImageView imagen, image_close;

    private OnFragmentInteractionListener mListener;


    public static Connection_problem newInstance(Boolean param1) {
        Connection_problem fragment = new Connection_problem();
        Bundle args = new Bundle();
        args.putBoolean(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        try {
            super.show(manager, tag);
        } catch (IllegalStateException e) {
            // ignore
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {

            condicion = getArguments().getBoolean("error");

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        View view = inflater.inflate(R.layout.popup_connection_problem, container, false);
        TextView textPrincipal = (TextView)view.findViewById(R.id.textPrincipal);
        TextView textSecundario = (TextView)view.findViewById(R.id.textSecundario);
        Button btn = (Button)view.findViewById(R.id.botonAceptar);
        RelativeLayout principal = (RelativeLayout) view.findViewById(R.id.relativePadre);
        imagen = (ImageView) view.findViewById(R.id.imagen);
        image_close = (ImageView) view.findViewById(R.id.imageView_close);
        image_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();

            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();

            }
        });

        if(condicion){
            this.getDialog().getWindow().setBackgroundDrawableResource(R.drawable.back_conn_error);
            imagen.setImageResource(R.drawable.error01);
            principal.setBackgroundResource(R.color.connection_error);
            textPrincipal.setText(R.string.conn_error);
            textSecundario.setText(R.string.conn_error2);

        }else{
            this.getDialog().getWindow().setBackgroundDrawableResource(R.drawable.back_conn_warning);
            imagen.setImageResource(R.drawable.error02);
            principal.setBackgroundResource(R.color.connection_warning);
            textPrincipal.setText(R.string.conn_warning);
            textSecundario.setText(R.string.conn_warning2);
        }
        return view;

    }


    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }
}
