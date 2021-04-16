package com.example.notesapp

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_not_ekle.*
import kotlinx.android.synthetic.main.fragment_not_guncelle.view.*

class NotGuncelleFragment : Fragment() {

    private val args: NotGuncelleFragmentArgs by navArgs<NotGuncelleFragmentArgs>()
    private lateinit var editTextNotTitleGun: EditText
    private lateinit var editTextNotTextGun: EditText
    private lateinit var noteViewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_not_guncelle, container, false)

        editTextNotTitleGun = view.findViewById<EditText>(R.id.editTextNotTitleGun)
        editTextNotTextGun = view.findViewById(R.id.editTextNotTextGun)
        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        editTextNotTitleGun.setText(args.Note.noteTitle)
        editTextNotTextGun.setText(args.Note.noteText)

        view.button_guncelle.setOnClickListener {
            notGuncelle()
        }

        hideWhenClick()

        return view
    }

    private fun notGuncelle() {
        val notTitle = editTextNotTitleGun.text.toString()
        val notText = editTextNotTextGun.text.toString()
        if (checkIfNull(notTitle, notText)) {
            val note = Note(args.Note.noteId, notTitle, notText)
            noteViewModel.notGuncelle(note)
            Toast.makeText(requireContext(), "Notunuz güncellendi", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_notGuncelleFragment_to_listeFragment)
        } else {
            Toast.makeText(requireContext(), "Başlık veya açıklama kısmı boş", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun checkIfNull(notTitle: String, notText: String): Boolean {
        return !(TextUtils.isEmpty(notTitle) && TextUtils.isEmpty(notText))
    }

    fun View.hideKeyboard() {
        val inputManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(windowToken, 0)
    }

    fun hideWhenClick() {
        editTextNotTitleGun.setOnFocusChangeListener { view, b ->
            if (!b) {
                view.hideKeyboard()
            }
        }
        editTextNotTextGun.setOnFocusChangeListener { view, b ->
            if (!b) {
                view.hideKeyboard()
            }
        }
    }
}