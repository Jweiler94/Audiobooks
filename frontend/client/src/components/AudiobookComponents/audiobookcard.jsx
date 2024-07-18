export default function Audiobook({ book }) {
      return (
            <li classname="book-card">
                  <hgroup>
                        <h4>Title: {book.title}</h4>
                        <small>Author: {book.author}</small>
                        <small>Publication Year: {book.year}</small>
                        <small>Length: {book.length}</small>
                        <small>Book Description: 
                              {book.description}
                        </small>
                  </hgroup>
            </li>
      );
}