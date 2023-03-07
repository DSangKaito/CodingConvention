--------------------------------Coding Convention-------------------------------------
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