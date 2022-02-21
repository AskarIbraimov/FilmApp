package kg.geektech.filmapp.ui.films;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import kg.geektech.filmapp.data.models.Film;
import kg.geektech.filmapp.databinding.ItemFilmBinding;

public class FilmsAdapter extends RecyclerView.Adapter<FilmsAdapter.FilmViewHolder> {

    private List<Film> films = new ArrayList<>();
    private OnItemClick listener;

    public FilmsAdapter(OnItemClick listener) {
        this.listener = listener;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FilmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFilmBinding binding = ItemFilmBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new FilmViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmViewHolder holder, int position) {
        holder.onBind(films.get(position));

    }

    @Override
    public int getItemCount() {
        return films.size();
    }

    public class FilmViewHolder extends RecyclerView.ViewHolder{
        private ItemFilmBinding binding;
        public FilmViewHolder(@NonNull ItemFilmBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBind(Film film) {
            binding.title.setText(film.getTitle());
            binding.description.setText(film.getDescription());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClick(film);
                }
            });

        }
    }
}
