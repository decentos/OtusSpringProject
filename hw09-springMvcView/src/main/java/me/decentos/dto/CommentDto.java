package me.decentos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.decentos.model.Book;
import me.decentos.model.Comment;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private long id;
    private String commentary;
    private Book book;

    public static Comment toDomainObject(CommentDto dto) {
        return new Comment(dto.getId(), dto.getCommentary(), dto.getBook());
    }

    public static CommentDto toDto(Comment comment) {
        return new CommentDto(comment.getId(), comment.getCommentary(), comment.getBook());
    }
}
