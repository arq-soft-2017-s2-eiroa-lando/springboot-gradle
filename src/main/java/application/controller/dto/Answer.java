package application.controller.dto;

public class Answer {

	private int id;
	private String option;

	public Answer(int id, String option) {
		super();
		this.id = id;
		this.option = option;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public Answer() {
	}

}
