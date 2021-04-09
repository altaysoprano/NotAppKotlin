@file:JvmName("KeyboardUtils")
package com.example.notesapp

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_liste.view.*
import kotlinx.android.synthetic.main.fragment_not_ekle.*
import kotlinx.android.synthetic.main.fragment_not_ekle.view.*

class NotEkleFragment : Fragment() {

    private lateinit var editTextNotTitle: EditText
    private lateinit var editTextNotText: EditText
    private lateinit var noteViewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val fragmentView = inflater.inflate(R.layout.fragment_not_ekle, container, false)

        editTextNotTitle = fragmentView.editTextNotTitle
        editTextNotText = fragmentView.editTextNotText
        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        hideWhenClick()

        fragmentView.button_ekle.setOnClickListener {
            notEkle()
        }

        return fragmentView
    }

    private fun notEkle() {
        val notTitle = editTextNotTitle.text.toString()
        val notText = editTextNotText.text.toString()
        if (checkIfNull(notTitle, notText)) {
            val note = Note(0, notTitle, notText)
            noteViewModel.notEkle(note)
            Toast.makeText(requireContext(), "Notunuz eklendi", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_notEkleFragment_to_listeFragment)
        }
        else {
            Toast.makeText(requireContext(), "Başlık veya açıklama kısmı boş", Toast.LENGTH_SHORT).show()
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
            editTextNotTitle.setOnFocusChangeListener { view, b ->
                if (!b) {
                    view.hideKeyboard()
                }
            }
            editTextNotText.setOnFocusChangeListener { view, b ->
                if (!b) {
                    view.hideKeyboard()
                }
            }


        }

    }
