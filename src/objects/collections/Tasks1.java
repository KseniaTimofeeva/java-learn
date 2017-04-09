package objects.collections;

import objects.collections.inner.Message;
import objects.collections.inner.MessagePriority;
import objects.collections.inner.MessageGenerator;

import java.util.*;

/**
 * Created by xmitya on 17.10.16.
 */
public class Tasks1 {

    public static void main(String[] args) {
        MessageGenerator generator = new MessageGenerator();

        List<Message> messages = generator.generate(100);

        countEachPriority(messages);
        countCountEachCode(messages);
        countUniqueMessages(messages);

        System.out.println("Genuine messages in natural order: \n" + genuineMessagesInOriginalOrder(messages) + "\n");

        sortByPriority(messages);
        removeEach(generator.generate(100), MessagePriority.LOW);
        removeOther(generator.generate(100), MessagePriority.URGENT);
    }

    private static void countEachPriority(List<Message> messages) {
        // Сосчитайте количество сообщений для каждого приоритета.
        // Ответ необходимо вывести в консоль.
        Map<MessagePriority, Integer> mapEachPriority = new EnumMap<>(MessagePriority.class);
        for (MessagePriority mp : MessagePriority.values()) {
            mapEachPriority.put(mp, 0);
        }
        for (Message message : messages) {
            MessagePriority key = message.getPriority();
            Integer currentQty = mapEachPriority.get(key);
            mapEachPriority.put(key, currentQty + 1);
        }
        System.out.println("Qty of each priority: ");
        for (Map.Entry<MessagePriority, Integer> entry : mapEachPriority.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println();
    }

    private static void countCountEachCode(List<Message> messages) {
        // Сосчитайте количество сообщений для каждого кода сообщения.
        // Ответ необходимо вывести в консоль.
        Map<Integer, Integer> mapEachCode = new HashMap<>();
        for (Message message : messages) {
            Integer key = message.getCode();
            Integer currentQty = mapEachCode.get(key);
            if (currentQty == null) {
                currentQty = 0;
            }
            mapEachCode.put(key, currentQty + 1);
        }
        System.out.println("Qty of each code: ");
        for (Map.Entry<Integer, Integer> entry : mapEachCode.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println();
    }

    private static void countUniqueMessages(List<Message> messages) {
        // Сосчитайте количество уничальных сообщений.
        // Ответ необходимо вывести в консоль.
        Set<Integer> uniqueMessages = new HashSet<>();
        for (Message message : messages) {
            uniqueMessages.add(message.getCode());
        }
        System.out.println("Qty of unique messages: " + uniqueMessages.size() + "\n");
    }

    private static List<Message> genuineMessagesInOriginalOrder(List<Message> messages) {
        // Здесь необходимо вернуть только неповторяющиеся сообщения и в том порядке, в котором
        // они встречаются в первоначальном списке. Например, мы на входе имеем такие сообщения:
        // [{URGENT, 4}, {HIGH, 9}, {LOW, 3}, {HIGH, 9}],
        // то на выходе должны получить:
        // [{URGENT, 4}, {HIGH, 9}, {LOW, 3}].
        // Т.е. остались только уникальные значения, и порядок их поступления сохранен.
        List<Message> uniqueMessages = new ArrayList<>();
        for (Message message : messages) {
            if (!uniqueMessages.contains(message)) {
                uniqueMessages.add(message);
            }
        }
        return uniqueMessages;
    }

    private static void removeEach(Collection<Message> messages, MessagePriority priority) {
        // Удалить из коллекции каждое сооющение с заданным приоритетом.
        System.out.printf("Before remove each: %s, %s\n", priority, messages);
        Iterator<Message> iter = messages.iterator();
        while (iter.hasNext()) {
            if (iter.next().getPriority() == priority) {
                iter.remove();
            }
        }
        System.out.printf("After remove each: %s, %s\n", priority, messages + "\n");
    }

    private static void removeOther(Collection<Message> messages, MessagePriority priority) {
        // Удалить из коллекции все сообщения, кроме тех, которые имеют заданный приоритет.
        System.out.printf("Before remove other: %s, %s\n", priority, messages);
        Iterator<Message> iter = messages.iterator();
        while (iter.hasNext()) {
            if (iter.next().getPriority() != priority) {
                iter.remove();
            }
        }
        System.out.printf("After remove other: %s, %s\n", priority, messages);
    }

    private static void sortByPriority(List<Message> messages/*, MessagePriority priority*/) {
        Collections.sort(messages);
        System.out.printf("Sorted by priority: %s\n\n", messages);
    }
}
