package com.example.notesapp

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_liste.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class ListeFragment : Fragment() {

    private lateinit var noteViewModel : NoteViewModel
    private var butunNotlar : List<Note> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_liste, container, false)

        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        val recyclerViewAdapter = RecyclerViewAdapter()
        val recyclerView = view.liste_recycler_view
        recyclerView.adapter = recyclerViewAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        noteViewModel.butunNotlar.observe(viewLifecycleOwner, Observer {
            recyclerViewAdapter.listeyiGuncelle(it)
        })

        view.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listeFragment_to_notEkleFragment)
        }

        return view

    }


}
