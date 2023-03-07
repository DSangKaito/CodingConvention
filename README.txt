  - Project về đặt vé xe
  - Phân công cho phần coding convention:
    + Sáng: chỉnh sửa file StatisticsServiceImpl.java (CodingConvention\src\main\java\com\example\datvexe\services\impl\StatisticsServiceImpl.java): Gồm chỉnh sửa về cách đặt tên (tiếng Vệt qua tiếng anh)
        Thêm comment để giải thích code
        Chỉnh sửa một số tên biến cho đúng qui tắc Coding convention (tên biến viết thường ký tự đầu, 
         phương thức bắt đầu bằng động từ, thuộc tính là danh từ)
        Chỉnh sửa một số giải thuật cho tiện lợi hơn
        Thực hiện add, commit, pull request, merge phần code của mình
        Thực hiện rebase -i
--------------------------------Coding Convention-------------------------------------
Phạm Hữu Tình
1.component "common":
  + Tại file "Payments.java":
    1.1 Sửa lại cách đặt tên của enum Payments - viết hoa mỗi chữ cái đầu của enum.
    1.2 Các constant ONLINE, OFFLINE - viết in hoa.
  + Tại file "Status.java":
    1.3 Sửa lại cách đặt tên của enum Status - viết hoa mỗi chữ cái đầu của enum.
    1.4 Các constant ACTIVE,INACTIVE,COMPLETED, NON_DEFINED - viết in hoa.
2.component "controllers":
   + Tại file "TicketController.java":
     2.1 Sửa lại cách đặt tên của class Ticket - viết hoa mỗi chữ cái đầu của enum.
     2.2 Tên các function viết theo nguyên tắc camel:
          - getAllTicketByUserId
          - getAllTicketByScheduleId
          - addTicket
          - updateTicket
          - deleteTicket
          - getStatus
     2.3 Tên các biến viết theo nguyên tắc camel nếu có hai từ trở lên:
          - id
          - ticket
          - ticketRequest
          - dataResponse
     2.4 Tên các constant viết in hoa:
          - BUS_COMPANY
          - USER
     2.5 Comment lại các chức năng của các hàm, các logic khó hiểu.
3.component "models":
  + Tại file "Ticket.java":
      3.1 Sửa lại cách đặt tên của class Ticket - viết hoa mỗi chữ cái đầu của enum.
      3.2 Tên các biến viết theo nguyên tắt camel:
            - ticket
            - id
            - numberSeat
            - dateOrder
            - dateReceive
            - busLine
            - userId
            - payMethod
            - verify
