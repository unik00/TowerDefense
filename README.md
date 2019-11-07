Ngô ơi hơi phức tạp kb t có giari thích nổi ko :)))

ông đọc lại cái link BLT: https://www.dropbox.com/s/oanjo44fghrol9s/BTL-OOP.pdf?dl=0&fbclid=IwAR0EnEKgphpT3zz9VzA6963Qmt6D-IV9dcPTmgHo0z6xWGUyldj5bMtUMSM

đọc kĩ phần GameStage với GameField ấy. 
Về cơ bản thì GameStage nó là một màn chơi kiểu level 1, 2, 3, ... ấy.
Nên là nó sẽ đọc dữ liệu từ file để load ra cái trạng thái init của level đấy (hiện là demo.txt).
Sau khi nó đọc xong thì nó chuyển vào cho GameField. 
GameField thì nó lưu thông tin của tất cả đối tượng đang có trong trò chơi.

Còn cái GameController là nó như kiểu class kiểm soát trạng thái liên tục của trò chơi. Render các thứ cũng ở trong này.

Ông đọc cái input file demo.txt nha t đang để là 0 là road còn 1 à mountain. Road vs Mountain thì t gán cho nó cái ảnh mặc định rồi khi render chỉ cần draw là ra.
Còn class Entity thì t thêm thuộc tính Image nhé. Hàm draw() nó thành hàm cha luôn mình ko cần phải override ở các hàm con nữa.

Config thì lưu một số thông tin thuộc tính của trò chơi, sửa gì thì chỉ cần thay đôi trong này thôi ko phải lôi từng hàm ra sửa.
Màn hình hiện t đang để size mặc định là (64 * 10) x (64 * 7).
Asset t lấy trong bộ 2 mục Default Size thì nó mặc định là 64x64 pixel cho mỗi ảnh.

Ông đọc xem có chỗ nào ko hiểu mai t giải thích cho :))) cũng lằng nhằng phết :v