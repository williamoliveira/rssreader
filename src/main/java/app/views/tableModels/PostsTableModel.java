package app.views.tableModels;

import shared.entities.post.Post;

public class PostsTableModel extends ResourceTableModel<Post>{

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Post post = getResources().get(rowIndex);

        switch (columnIndex) {
            case 0: return post.getTitle();
            case 1: return post.getDescription();
            default: return null;
        }
    }

    @Override
    public String[] getHeaders() {
        return new String[]{"Título", "Descrição"};
    }
}
