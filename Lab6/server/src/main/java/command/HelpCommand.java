package command;

import commands.AbstractCommand;
import commands.Command;
import response.Creator;
import studygroup.StudyGroup;

import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * �����-�������, ����������� ����� ���� ������, ��������� ��� ����������
 */
public class HelpCommand extends AbstractCommand {
    private final Creator creator;

    public HelpCommand(boolean req, Creator creator) {
        super(req);
        this.creator = creator;
    }

    public void execute() {
        creator.addToMsg("help : ������� ������� �� ��������� ��������\n" +
                "info : ������� � ����������� ����� ������ ���������� � ��������� (���, ���� �������������, ���������� ��������� � �.�.)\n" +
                "show : ������� � ����������� ����� ������ ��� �������� ��������� � ��������� �������������\n" +
                "add {element} : �������� ����� ������� � ���������\n" +
                "update id {element} : �������� �������� �������� ���������, id �������� ����� ���������\n" +
                "remove_by_id id : ������� ������� �� ��������� �� ��� id\n" +
                "clear : �������� ���������\n" +
                "save : ��������� ��������� � ����\n" +
                "execute_script file_name : ������� � ��������� ������ �� ���������� �����. � ������� ���������� ������� � ����� �� ����, � ������� �� ������ ������������ � ������������� ������.\n" +
                "exit : ��������� ��������� (��� ���������� � ����)\n" +
                "add_if_max {element} : �������� ����� ������� � ���������, ���� ��� �������� ��������� �������� ����������� �������� ���� ���������\n" +
                "remove_greater {element} : ������� �� ��������� ��� ��������, ����������� ��������\n" +
                "remove_lower {element} : ������� �� ��������� ��� ��������, �������, ��� ��������\n" +
                "print_ascending : ������� �������� ��������� � ������� �����������\n" +
                "print_unique_expelled_students : ������� ���������� �������� ���� expelledStudents ���� ��������� � ���������\n" +
                "print_field_descending_form_of_education : ������� �������� ���� formOfEducation ���� ��������� � ������� ��������");
    }

    public void execute(StudyGroup studyGroup) {
        throw new UnsupportedOperationException();
    }
}
