package com.example.listviewdemo

import android.content.Intent
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.listviewdemo.databinding.ActivityMainBinding
import com.example.listviewdemo.databinding.ListItemBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var textArray: Array<String>
    lateinit var imageArray: Array<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        textArray = resources.getStringArray(R.array.text_array)

        imageArray = arrayOf(
            R.drawable.dice1,
            R.drawable.dice2,
            R.drawable.dice3,
            R.drawable.dice4,
            R.drawable.dice5,
            R.drawable.dice6,
        )

        binding.lvDaten.adapter = MyAdapter()

        binding.lvDaten.setOnItemClickListener { _, _, position, _ ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("text", textArray[position])
            intent.putExtra("image", imageArray[position])
            startActivity(intent)
        }


    }

    inner class MyAdapter : BaseAdapter(){ // nur class ist nested class und kann nicht auf die äußere zugreife

        lateinit var itemView: ListItemBinding

        override fun getCount(): Int {
            return textArray.size
        }

        override fun getItem(position: Int): Any {
            return textArray[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
            itemView = ListItemBinding.inflate(layoutInflater)
            itemView.tvText.text = textArray[position]
            itemView.ivPic.setImageResource(imageArray[position])
            return itemView.root
        }
    }
}