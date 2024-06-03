package com.example.englishrussianflashcards.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.englishrussianflashcards.R
import com.example.englishrussianflashcards.domain.Card

/**
 * Created by Igor Aghibalov on 03.06.2024
 */
//TODO: refactor respecting card rotation animation
class ViewPagerAdapter(private val cardList: List<Card>): RecyclerView.Adapter<ViewPagerAdapter.CardViewHolder>() {


    class CardViewHolder(cardView: View): RecyclerView.ViewHolder(cardView) {
        private val englishWordTextView: TextView
        private val transcriptionTextView: TextView

        init {
            englishWordTextView = cardView.findViewById(R.id.english_word_text_view)
            transcriptionTextView = cardView.findViewById(R.id.transcription_text_view)
        }


        fun bindViewsToCardData(card: Card) {
            englishWordTextView.text = card.word
            transcriptionTextView.text = card.transcription
        }
    }

    override fun onCreateViewHolder(parentViewGroup: ViewGroup, viewType: Int): CardViewHolder {
        val layoutInflater = LayoutInflater.from(parentViewGroup.context)
        val view = layoutInflater.inflate(R.layout.card_front_side_layout, parentViewGroup, false)

        return CardViewHolder(view)
    }

    override fun getItemCount(): Int = cardList.size

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bindViewsToCardData(cardList[position])
    }
}