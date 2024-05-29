package com.example.englishrussianflashcards.presentation

import android.os.Bundle
import android.os.Process
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.englishrussianflashcards.R
import com.example.englishrussianflashcards.databinding.MainScreenLayoutBinding

/**
 * Created by Igor Aghibalov on 28.05.2024
 */

//TODO: create setFragmentAppendingTransactionOnButtonClick() method
class MainMenuFragment: Fragment() {
    private lateinit var mainMenuViewBinding: MainScreenLayoutBinding


    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        super.onCreateView(inflater, container, savedInstanceState)

        val inflatedRootView: View

        mainMenuViewBinding = MainScreenLayoutBinding.inflate(inflater, container, false)
        inflatedRootView = mainMenuViewBinding.root

        return inflatedRootView
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val appendedFragmentNameMap = mapOf(mainMenuViewBinding.continueButton to "CardLearningFragment",
                                            mainMenuViewBinding.cardsButton to "CardGroupsFragment",
                                            mainMenuViewBinding.newButton to "CardCreationFragment",
                                            mainMenuViewBinding.continueButton to "CardHistoryFragment")

        setFragmentAppendingTransactionOnButtonClick(appendedFragmentNameMap)

        mainMenuViewBinding.quitButton.setOnClickListener {
            Process.killProcess(Process.myPid())
        }
    }


    fun setFragmentAppendingTransactionOnButtonClick(appendedFragmentNameMap: Map<Button, String>) {

        appendedFragmentNameMap.forEach {
            val clickedButton = it.key
            val appendedFragmentName = it.value

            clickedButton.setOnClickListener {

                parentFragmentManager.commit {
                    replace(R.id.fragment_container_view, Class.forName(appendedFragmentName) as Class<out Fragment>, null)
                    setReorderingAllowed(true)
                    addToBackStack("$appendedFragmentName appending")
                }
            }
        }
    }
}