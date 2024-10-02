package com.example.pjboard.dto;

import com.example.pjboard.model.ArticleImage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class ArticleImageDto {
    private Long id;
    private String link;

    public static ArticleImageDto fromEntity(ArticleImage entity) {
        ArticleImageDto dto = new ArticleImageDto();
        dto.id = entity.getId();
        dto.link = entity.getLink();
        return dto;
    }
}
