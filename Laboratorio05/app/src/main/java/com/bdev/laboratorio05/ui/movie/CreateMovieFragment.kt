package com.bdev.laboratorio05.ui.movie

import android.os.Binder
import android.os.Bundle
import android.text.Spannable.Factory
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.bdev.laboratorio05.R
import com.bdev.laboratorio05.data.models.MovieModel
import com.bdev.laboratorio05.databinding.FragmentCreateMovieBinding

class CreateMovieFragment : Fragment() {


    // IMPLEMENTAR DATA BINDING


    private lateinit var binding: FragmentCreateMovieBinding


    // VIEW MODEL
    private val viewModel: MovieViewModel by activityViewModels { MovieViewModel.Factory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{

        // ACTIVIAR EL DATA BINDING
        binding = FragmentCreateMovieBinding.inflate(inflater, container, false)


        // RETORNAMOS EL BINDING.ROOT
       return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setViewModel()

        setOberserver()

    }

    private fun setViewModel(){
        binding.viewmodel = viewModel
    }

    private fun setOberserver(){

        viewModel.status.observe(viewLifecycleOwner){status ->
            when{
                status.equals(MovieViewModel.MOVIE_CREATED) -> {

                    Log.d("APP TAGG", status)
                    Log.d("APP TAGG", viewModel.getMovies().toString())

                    viewModel.clearState()
                    viewModel.clearData()

                    findNavController().popBackStack()
                }

                status.equals(MovieViewModel.WRONG_DATA) -> {
                    Log.d("APP TAGG", status)
                    viewModel.clearState()
                }
            }

        }
    }

}
