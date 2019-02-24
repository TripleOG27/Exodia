package examapp.domain.models.view;

public class DocumentsViewModel {
    private String id;
    private String title;


    public DocumentsViewModel() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if(title.length()>12) {
            this.title = title.substring(11) + "...";
        }else {
            this.title=title;
        }
    }


}
