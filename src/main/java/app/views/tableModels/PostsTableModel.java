package app.views.tableModels;

import shared.models.post.Post;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PostsTableModel extends ResourceTableModel<Post>{

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Post post = getResources().get(rowIndex);

        switch (columnIndex) {
            case 0: return post.getTitle();
            case 1: return post.getUrl();
            case 2: return dateToString(post.getPublishedAt());
            default: return null;
        }
    }

    @Override
    public String[] getHeaders() {
        return new String[]{"Título", "Link", "Publicado em"};
    }

    private String dateToString(Date date) {
        if (date == null) return "";

        return new SimpleDateFormat("dd/MM/YYYY HH:mm").format(date);
    }
}
