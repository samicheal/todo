@Entity
class Book(

@Id
private int id;


private String name;

@ManyToOne
@JoinColumn(name="book_category_id")
private BookCategory bookCategory;

book(){}

book(String name){

}

book(String name, String bookCategory){

}



)