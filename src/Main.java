import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main implements ActionListener {

    static JFrame frame = new JFrame("Викторина");
    static JPanel mainPanel = new JPanel();
    static JPanel buttonPanel = new JPanel();
    static JLabel question = new JLabel();
    static JLabel result = new JLabel();
    static JButton answer1 = new JButton(), answer2 = new JButton(), answer3 = new JButton(), answer4 = new JButton();
    static int width = 400, height = 300;
    static Quest [] quests = new Quest[10];

    public static void main(String[] args) {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setBounds(dim.width/2 - width/2, dim.height/2 - height/2, width, height);
        frame.setVisible(true);

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(question);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(buttonPanel);
        mainPanel.add(Box.createVerticalStrut(10));
        frame.add(mainPanel);

        buttonPanel.setLayout(new GridLayout(2,2, 10,10));
        buttonPanel.setMaximumSize(new Dimension(width-40, height - 100));
        buttonPanel.add(answer1);
        buttonPanel.add(answer2);
        buttonPanel.add(answer3);
        buttonPanel.add(answer4);

        question.setMaximumSize(new Dimension(width-40, 80));
        question.setAlignmentX(Component.CENTER_ALIGNMENT);


        for (int i = 0; i < 10; i++) {
            quests[i] = new Quest();
        }
        quests[0].setQuestion("От кого не смог уйти колобок?", "Волк", "Дедушка", "Лиса", "Заяц", "Лиса");
        quests[1].setQuestion("На каком языке написана эта программа?", "Java", "Kotlin", "PHP", "Python", "Java");
        quests[2].setQuestion("Какой итальянский футбольный клуб называют Старой синьорой?", "Милан", "Ювентус", "Интер", "Рома", "Ювентус");
        quests[3].setQuestion("Какой спартаковский футболист в матче с Силькеборгом забил 2 гола, встал в ворота и отбил штрафной?", "Титов", "Цымбаларь", "Аленичев", "Тихонов", "Тихонов");
        quests[4].setQuestion("Каким стиле владел мастер, по легенде, убивший Брюса Ли?", "Вин чун", "Тай цзи", "Дим мак", "Шаолинь цюань", "Дим мак");
        quests[5].setQuestion("Родина буддизма?", "Китай", "Корея", "Япония", "Индия", "Индия");
        quests[6].setQuestion("Кто из вратарей был единственным получившим Золотой мяч?", "Буффон", "Яшин", "Кан", "Шмейхель", "Яшин");
        quests[7].setQuestion("Кто царствовал при присоединении Сибири?", "Пётр Первый", "Иван Калита", "Иван Грозный", "Екатерина Вторая", "Иван Грозный");
        quests[8].setQuestion("В каком году состоялось Ледовое побоище?", "1242", "1241", "1245", "1239", "1242");
        quests[9].setQuestion("Сколько раз нападало на Русь Казанское ханство после правления Ивана Грозного?", "3", "8", "4", "0", "0");

        //int wrightCount = 0;

        Main listen = new Main();
        answer1.addActionListener(listen);
        answer2.addActionListener(listen);
        answer3.addActionListener(listen);
        answer4.addActionListener(listen);

        initQuest (quests[0]);
    }

    public static void initQuest (Quest quest) {
        question.setText(String.format("<html><body style='width: %1spx'>%1s", width-120, quest.question));
        answer1.setText(quest.answer1);
        answer2.setText(quest.answer2);
        answer3.setText(quest.answer3);
        answer4.setText(quest.answer4);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (Quest.answerNumber < 9) {
            if (actionEvent.getActionCommand().equals(quests[Quest.answerNumber].getWrightAnswer())) {
                Quest.wrightAnswerCount++;
            }
            Quest.answerNumber++;
            initQuest(quests[Quest.answerNumber]);
        } else {
            if (actionEvent.getActionCommand().equals(quests[Quest.answerNumber].getWrightAnswer())) {
                Quest.wrightAnswerCount++;
            }
            mainPanel.remove(question);
            mainPanel.remove(buttonPanel);
            mainPanel.add(result);
            result.setAlignmentX(Component.CENTER_ALIGNMENT);
            result.setAlignmentY(Component.CENTER_ALIGNMENT);
            result.setText("Ваш результат: " + Quest.wrightAnswerCount + "/" + (Quest.answerNumber+1) + " верных ответов");
        }
    }
}

class Quest {
    static int answerNumber = 0;
    static int wrightAnswerCount = 0;

    String question;
    String answer1, answer2, answer3, answer4;
    private String wrightAnswer;

    public Quest () {
        question = "";
        answer1 = ""; answer2 = ""; answer3 = ""; answer4 = "";
        wrightAnswer = "";
    }

    public void setQuestion(String question, String answer1, String answer2, String answer3, String answer4, String wrightAnswer) {
        this.question = question;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.wrightAnswer = wrightAnswer;
    }

    public String getWrightAnswer() {
        return wrightAnswer;
    }
}