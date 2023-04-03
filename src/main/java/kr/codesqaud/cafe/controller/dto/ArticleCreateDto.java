package kr.codesqaud.cafe.controller.dto;

import kr.codesqaud.cafe.domain.Article;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class ArticleCreateDto {
    @NotBlank(message = "제목을 입력하세요.")
    @Size(min = 2, max = 30, message = "제목은 {min} ~ {max} 길이로 입력하세요.")
    private String title;

    @NotBlank(message = "아이디를 입력하세요.")
    @Size(min = 5, max = 20, message = "아이디는 {min} ~ {max} 길이로 입력하세요.")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "아이디는 알파벳, 숫자만 가능합니다.")
    private String userId;

    @Nullable
    @Size(max = 1000, message = "본문은 {max}글자 이하로 입력하세요.")
    private String contents;

    public ArticleCreateDto(String title, String userId, String contents) {
        this.title = title;
        this.userId = userId;
        this.contents = contents;
    }

    public ArticleCreateDto() {
    }

    public String getTitle() {
        return title;
    }

    public String getUserId() {
        return userId;
    }

    public String getContents() {
        return contents;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setContents(@Nullable String contents) {
        this.contents = contents;
    }

    public Article toArticle() {
        if (isEmptyContents()) {
            contents = "";
        }

        return new Article(title, userId, contents);
    }

    private boolean isEmptyContents() {
        return !StringUtils.hasText(contents);
    }
}