package tr.gov.hmb.survey.enums;

public enum Answer {
    YES("Evet"), NO("Hayır");

    private final String title;

    Answer(String title) {
        this.title = title;
    }

    public String getTitle(Answer answer) {
        return answer.title;
    }
}
