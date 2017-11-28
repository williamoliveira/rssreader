package app.views.tableModels;

import shared.models.post.Post;

public class PostsTableModel extends ResourceTableModel<Post>{

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Post post = getResources().get(rowIndex);

        switch (columnIndex) {
            case 0: return post.getTitle();
            case 1: return post.getUrl();
            default: return null;
        }
    }

    @Override
    public String[] getHeaders() {
        return new String[]{"Título", "Link"};
    }

}
