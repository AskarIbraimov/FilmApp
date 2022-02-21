package kg.geektech.filmapp.ui.films_detail;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import kg.geektech.filmapp.App;
import kg.geektech.filmapp.R;
import kg.geektech.filmapp.data.models.Film;
import kg.geektech.filmapp.databinding.FragmentFilmDetailBinding;
import kg.geektech.filmapp.databinding.FragmentFilmsBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilmDetailFragment extends Fragment {

    private FragmentFilmDetailBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentFilmDetailBinding.inflate(inflater);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        assert getArguments() != null;
        String id = getArguments().getString("id");

        App.api.getFilmById(id).enqueue(new Callback<Film>() {
            @Override
            public void onResponse(Call<Film> call, Response<Film> response) {
                Film film = response.body();
                binding.title.setText(film.getTitle());
                binding.director.setText(film.getDirector());
                binding.producer.setText(film.getProducer());
                binding.releaseDate.setText(film.getReleaseDate());
                binding.rtScore.setText(film.getRtScore());
                Glide.with(requireActivity()).load(film.getImage()).into(binding.image);
            }

            @Override
            public void onFailure(Call<Film> call, Throwable t) {

            }
        });
    }
}