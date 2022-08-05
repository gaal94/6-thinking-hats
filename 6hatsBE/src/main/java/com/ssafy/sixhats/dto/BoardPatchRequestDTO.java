@Getter
@NoArgsConstructor
public class BoardUpdateRequestDto {
    private String title;
    private String board_contents;;

    @Builder
    public BoardUpdateRequestDto(String title, String content) {
        this.title = title;
        this.board_contents = board_contents;
    }
}