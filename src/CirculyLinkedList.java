import java.util.Objects;

public class CirculyLinkedList<E> {
  private Node<E>head=null;
    private  Node<E>tail=null;
    private  int size=0;
    public CirculyLinkedList(){

    }
    public boolean isEmpty(){
        return size==0;

    }
//    public int size(){
//        return size;
//    }
    public E first(){
        if (isEmpty())return null;
        return tail.next.element;
    }
    public E last(){
        if (isEmpty())return null;
        return tail.element;
    }
    public void rotate(){
        if (tail!=null)
            tail=tail.next;

    }
//    public void addFirst(E e){
//        if(size==0){
//            tail=new Node<>(e,null);
//            tail.setNext(tail);
//        }else{
//            Node<E>newest=new Node<>(e, tail.getNext());
//            tail.setNext(newest);
//        }
//        size++;
//    }

    public void addLast(E e){
        addFirst(e);
        tail=tail.getNext();
    }
    public E removeFirst(){
        if(isEmpty())return null;
        Node<E>head=tail.getNext();
        if(head==tail)tail=null;
        else tail.setNext(head.getNext());
        size--;
        return head.getElement();
    }
//>>>>>>>>>>>>>>>>>>>>>>>>>******<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
//   Q1.   Consider the implementation of CircularlyLinkedList.addFirst, in Code Fragment 3.16.
//   The else body at lines 39 and 40 of that method relies on a locally declared variable, newest.
//   Redesign that clause to avoid use of any local variable.
 public void addFirst(E e) {
     if (tail == null) {
         head = new Node<>(e );
     } else {
         tail.next = new Node<>(e);
     }
 }
//>>>>>>>>>>>>>>>>>>>>>>>>>******<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
//   Q2.   Give an implementation of the size( ) method for the CircularlyLinkedList class,
//   assuming that we did not maintain size as an instance variable.

    public int size() {
        if (head == null) {
            return 0; // القائمة فارغة
        }

        Node<E> current = head;
        int count = 1;

        // استمر في التكرار حتى تصل مرة أخرى إلى العقدة الأولى
        do {
            current = current.next;
            count++;
        } while (current != head);

        return count;
    }
 //>>>>>>>>>>>>>>>>>>>>>>>>>******<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
//       Q3.    Implement the equals( ) method for the CircularlyLinkedList class,
//       assuming that two lists are equal if they have the same sequence of elements,
//       with corresponding elements currently at the front of the list.


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CirculyLinkedList<E> that = (CirculyLinkedList<E>) o;
        if (head == null && that.head == null) {
            return true; // كلتا القائمتين فارغتين
        }

        // تحقق إذا كانت أي قائمة فارغة والأخرى ليست كذلك
        if (head == null || that.head == null) {
            return false;
        }

        Node<E> current1 = head;
        Node<E> current2 = that.head;

        // استمر في التكرار حتى تصل إلى عقدة فارغة في إحدى القائمتين
        do {
            if (!current1.element.equals(current2.element)) {
                return false; // عدم تطابق البيانات
            }
            current1 = current1.next;
            current2 = current2.next;
        } while (current1 != null && current2 != null);

        // تحقق من طول القائمتين (يجب أن يكونا متساويين)
        return current1 == null && current2 == null;


    }
 //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>******<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
//       Q4.     Suppose you are given two circularly linked lists, L and M.
//       Describe an algorithm for telling if L and M store the same sequence of elements
//       (but perhaps with different starting points).

//خوارزمية لتحديد ما إذا كانت قائمتان مرتبطتان دائريتان L و M تحتويان على نفس تسلسل العناصر:
//
//    تحقق من الحالات الخاصة:
//        إذا كانت كلتا القائمتين L و M فارغتين، فهما متساويتان.
//        إذا كانت إحداهما فقط فارغة، فهما غير متساويتان.
//
//    ابحث عن عقدة بداية عشوائية في إحدى القائمتين (لنقل L):
//        اختر أي عقدة في L كعقدة البداية.
//
//    ابدأ بعقدتي مؤشر current1 و current2، حيث تشير current1 إلى العقدة المختارة في L و current2 تشير إلى رأس القائمة M.
//
//    التكرار ومقارنة العناصر:
//        استمر في تحريك current1 و current2 إلى العقدة التالية في القائمتين المعنيتين على التوالي.
//        في كل تكرار، قارن البيانات المخزنة في current1.data و current2.data.
//            إذا لم تتطابق البيانات، فهذا يعني أن القائمتين غير متساويتين ويتم إرجاع القيمة false.
//
//    فحص دورة كاملة:
//        استمر في التكرار حتى تصل إحدى الحالتين:
//            تصل كلتا current1 و current2 إلى عقدة فارغة، مما يعني أن القائمتين قد أكملتا دورة كاملة.
//            تصل current1 إلى العقدة المختارة في البداية (عقدة البداية العشوائية في L)، مما يعني أن L قد أكملت دورة كاملة.
//
//    تحديد المساواة:
//        إذا وصلت كلتا current1 و current2 إلى عقدة فارغة بعد مقارنة جميع العناصر بنجاح، فهذا يعني أن القائمتين تحتويان على نفس تسلسل العناصر (بترتيب دائري مختلف محتمل).
//        في أي حالة أخرى، تعتبر القائمتان غير متساويتين.
//
//ملاحظة:
//
//    هذه الخوارزمية تعمل بغض النظر عن عقدة البداية المختارة في L، حيث ستكمل كلا القائمتين دورة كاملة إذا كانتا تحتويان على نفس تسلسل العناصر.
//    تعقيد الوقت والمساحة لهذه الخوارزمية تعتبر خطية في عدد العناصر في القائمتين، حيث نحتاج إلى التكرار عبر كلتا القائمتين في أسوأ سيناريو.
//
//مثال:
//
//لنفترض أن L = {1, 2, 3} و M = {2, 3, 1}.
//
//    L ليست فارغة و M ليست فارغة.
//    يتم اختيار عقدة تحتوي على القيمة 1 في L كعقدة البداية.
//    current1 يشير إلى العقدة التي تحتوي على القيمة 1 في L، و current2 يشير إلى رأس M (العقدة التي تحتوي على القيمة 2).
//    تتكرر العملية:
//        تتم مقارنة البيانات (1 و 2)، لا تتطابق، لذلك يتم إرجاع القيمة false (القائمتان غير متساويتان).
//
//بناءً على هذا المثال، نستنتج أن L و M لا تحتويان على نفس تسلسل العناصر.

 //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>******<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

//    Q5.    Given a circularly linked list L containing an even number of nodes, describe how to split
//    L into two circularly linked lists of half the size.
//

//    خوارزمية لتقسيم قائمة مرتبطة دائرية L تحتوي على عدد زوجي من العقد إلى قائمتين دائريتين من نصف الحجم:
//
//    تحقق من عدد العقد: تأكد من أن القائمة L تحتوي على عدد زوجي من العقد. يمكنك تحقيق ذلك باستخدام طريقة size() منفصلة أو باستخدام تقنية "البطيئة والسريعة" لفحص دورة واحدة.
//
//    ابحث عن العقدة الوسطى:
//        استخدم تقنية "البطيئة والسريعة" للعثور على العقدة الوسطى في القائمة.
//            ابدأ بمؤشرين slow و fast، حيث يشير slow إلى رأس القائمة ويشير fast إلى العقدة التالية بعد slow.
//            استمر في تحريك slow بمقدار خطوة واحدة و fast بمقدار خطوتين في كل تكرار.
//            عندما يصل fast إلى عقدة فارغة أو إلى رأس القائمة (بسبب الدورية)، فإن slow سيشير إلى العقدة الوسطى.
//
//    اقطع القائمة:
//        احصل على عقدة ما قبل الوسطى باستخدام slow.next.
//        اضبط next للعقدة التي سبقت الوسطى (slow.next.next) ليُشير إلى رأس القائمة، مما يفصل القائمة الأصلية إلى قسمين.
//
//    إنشاء قائمتين جديدتين:
//        حدد رأس القائمة الأولى على رأس القائمة الأصلية (L.head).
//        حدد رأس القائمة الثانية على العقدة التالية للوسطى (slow.next).
//
//    إرجاع القائمتين المنفصلتين:
//        أعد كل من رأس القائمة الأولى والثانية، مما يمثل القائمتين المنفصلتين من نصف الحجم.
//
//مثال:
//
//لنفترض أن القائمة المرتبطة الدائرية الأصلية L هي {1, 2, 3, 4}.
//
//    L تحتوي على عدد زوجي من العقد (4).
//    باستخدام تقنية "البطيئة والسريعة"، ستشير slow إلى العقدة التي تحتوي على القيمة 2 (العقدة الوسطى).
//    يتم ضبط next للعقدة التي تسبق الوسطى (العقدة التي تحتوي على القيمة 1) لتشير إلى رأس القائمة الأصلية، مما يفصل القائمة إلى قسمين.
//    رأس القائمة الأولى يظل هو رأس القائمة الأصلية (العقدة التي تحتوي على القيمة 1).
//    رأس القائمة الثانية هو العقدة التالية للوسطى (العقدة التي تحتوي على القيمة 3).
//
//الآن، أصبحت القائمة مقسمة إلى قائمتين دائريتين منفصلتين:
//
//    القائمة الأولى: {1, 2}
//    القائمة الثانية: {3, 4}
//
//ملحوظة:
//
//    هذه الخوارزمية تفترض أن عدد العقد في القائمة زوجي.
//  يمكن تعديل الخوارزمية للتعامل مع الحالات الخاصة في حالة وجود عدد فردي من العقد.
//
// توضيح:
//
//    قنية "البطيئة والسريعة" لفحص دورة واحدة في قائمة مرتبطة:
//
//الهدف:
//
//تُستخدم تقنية "البطيئة والسريعة" لتحديد ما إذا كانت قائمة مرتبطة تحتوي على دورة واحدة أم لا. تعمل هذه التقنية من خلال تتبع مسارين مختلفين في القائمة باستخدام مؤشرين "بطيء" و "سريع".
//
//الطريقة:
//
//    ابدأ بمؤشرين:
//        slow: يشير إلى رأس القائمة.
//        fast: يشير إلى العقدة التالية بعد slow.
//
//    التكرار:
//        في كل تكرار:
//            تحرك المؤشر slow خطوة واحدة إلى الأمام.
//            تحرك المؤشر fast خطوتين إلى الأمام.
//
//    شروط التوقف:
//        الحالة 1: إذا وصل fast إلى عقدة فارغة، فهذا يعني أن القائمة لا تحتوي على دورة.
//        الحالة 2: إذا وصل fast إلى نفس العقدة مثل slow (أو أي عقدة سابقة تم زيارتها بواسطة slow)، فهذا يعني أن القائمة تحتوي على دورة.
//
//الشرح:
//
//    نظرًا لأن fast يتحرك أسرع من slow، فسيصل fast إلى نهاية القائمة (أو إلى عقدة تم زيارتها سابقًا) قبل slow إذا كانت القائمة تحتوي على دورة.
//    إذا لم تحتوي القائمة على دورة، سيصل fast إلى عقدة فارغة قبل slow.
//
//مثال:
//
//لنفترض أن لدينا القائمة التالية:
//
//1 -> 2 -> 3 -> 4 -> 2
//
//    slow يشير إلى العقدة 1 و fast يشير إلى العقدة 2.
//    في التكرار الأول:
//        slow يتحرك إلى العقدة 2.
//        fast يتحرك إلى العقدة 4.
//    في التكرار الثاني:
//        slow يتحرك إلى العقدة 3.
//        fast يتحرك إلى العقدة 2 (نفس العقدة التي يشير إليها slow).
//
//في هذه الحالة، نستنتج أن القائمة تحتوي على دورة.

//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>******<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

//    Q6.   Implement the clone( ) method for the CircularlyLinkedList class

    public CirculyLinkedList<E> clone() {
//        تنشئ نسخة مستقلة عن القائمة المرتبطة الدائرية الأصلية
        CirculyLinkedList<E> copy = new CirculyLinkedList<>();

        // تحقق من القائمة الفارغة
        if (head == null) {
            return copy; // لا حاجة لنسخ القائمة الفارغة
        }

        // ابحث عن العقدة الأخيرة في القائمة الأصلية
        Node<E> current = head;
        while (current.next != head) {
            current = current.next;
        }

        // استخدم تقنية "البطيئة والسريعة" للنسخ
        Node<E> newHead = new Node<>(current.element); // إنشاء رأس القائمة المنسوخة
        copy.head = newHead;
        Node<E> newCurrent = newHead;
        current = head;

        do {
            current = current.next;
            newCurrent.next = new Node<>(current.element);
            newCurrent = newCurrent.next;
        } while (current != head);

        // إصلاح الحلقة في القائمة المنسوخة
        newCurrent.next = newHead;

        return copy;
    }
 //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>******<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    private static class Node<E>{

        E element;
        Node<E>next;



        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }

        public Node(E e) {
        }

        public E getElement() {
            return element;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }
    }

}

