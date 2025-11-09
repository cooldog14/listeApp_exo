package com.example.listeapp

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.listeapp.adapters.TaskAdapter
import com.example.listeapp.models.TaskItem

class MainActivity : AppCompatActivity() {

    private lateinit var listViewTasks: ListView
    private lateinit var buttonAdd: Button
    private lateinit var taskAdapter: TaskAdapter
    private var tasks = mutableListOf<TaskItem>()
    private var nextId = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialisation des vues
        listViewTasks = findViewById(R.id.listViewTasks)
        buttonAdd = findViewById(R.id.buttonAdd)

        // Création de données mockées
        createMockData()

        // Configuration de l'adapter
        taskAdapter = TaskAdapter(this, tasks)
        listViewTasks.adapter = taskAdapter

        // Gestion du clic sur le bouton Ajouter
        buttonAdd.setOnClickListener {
            showAddTaskDialog()
        }

        // TODO: Ajouter la gestion du clic sur un élément pour la modification ici
        // TODO: Ajouter la gestion du long clic sur un élément pour la suppression ici
    }

    private fun createMockData() {
        tasks.add(TaskItem(nextId++, "Faire les courses", "Acheter du lait, du pain et des œufs"))
        tasks.add(TaskItem(nextId++, "Appeler le médecin", "Prendre rendez-vous pour vendredi"))
        tasks.add(TaskItem(nextId++, "Réviser pour l'examen", "Chapitres 1 à 5 du livre"))
    }

    private fun showAddTaskDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_item, null)
        val editTextName = dialogView.findViewById<EditText>(R.id.editTextTaskName)
        val editTextDescription = dialogView.findViewById<EditText>(R.id.editTextTaskDescription)
        val buttonCancel = dialogView.findViewById<Button>(R.id.buttonCancel)
        val buttonValidate = dialogView.findViewById<Button>(R.id.buttonValidate)

        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .create()

        buttonCancel.setOnClickListener {
            dialog.dismiss()
        }

        buttonValidate.setOnClickListener {
            val name = editTextName.text.toString().trim()
            val description = editTextDescription.text.toString().trim()

            if (name.isNotEmpty()) {
                // Ajout du nouvel élément à la liste
                val newTask = TaskItem(nextId++, name, description)
                tasks.add(newTask)

                // Rafraîchissement de la ListView
                taskAdapter.notifyDataSetChanged()

                // Affichage du Toast de confirmation
                Toast.makeText(this, getString(R.string.task_added), Toast.LENGTH_SHORT).show()

                dialog.dismiss()
            } else {
                editTextName.error = "Le nom ne peut pas être vide"
            }
        }

        dialog.show()
    }
}