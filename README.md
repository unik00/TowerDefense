**

# Tower Defense

Project Bài tập lớn môn Lập trình hướng đối tượng của nhóm CsPhAi
Nhóm em gồm:
- Bùi Quang Long
- Ngô Đức Huy

## Tính năng game
- **Quân địch**: Mỗi loại quân địch có các chỉ số khác nhau về máu, tốc độ di chuyển và phần thưởng (tiền) nhận được khi bị tiêu diệt. Có 4 loại quân địch:
-- Normal Enemy: Quân địch thường
-- Tanker Enemy: Quân địch nhiều máu, di chuyển chậm
-- Smaller Enemy: Quân địch có kích thước nhỏ, di chuyển nhanh.
-- Boss Enemy: Quân địch có rất nhiều máu và di chuyển rất chậm.
- **Tháp**: Mỗi tháp có các chỉ số khác nhau về giá mua, sát thương, tầm bắn, tốc độ bắn. Có 3 loại tháp: 
-- Normal Tower: Tháp thường, các chỉ số ở mức trung bình.
-- Sniper Tower: Tháp bắn tỉa, bắn chậm, tầm bắn xa nhưng sát thương cao.
-- Machine Gun Tower: Tháp liên thanh, bắn nhanh, tầm bắn ngắn, sát thương thấp
- **Balance**: Lượng tiền hiện có.
-- Tiền dùng để mua tháp.
-- Khi tiêu diệt quân địch, tiền sẽ được tăng lên tùy vào loại quân địch.
- Mạng của người chơi: Khi bắt đầu trò chơi, người chơi sẽ có 5 mạng (tương ứng với 5 trái tim). Mỗi lần có 1 quân địch đi được đến điểm kết thúc thì người chơi sẽ mất 1 mạng.
- **Luật chơi:**
-- Trò chơi có 4 levels. Trò chơi bắt đầu khi người chơi nhấn vào nút *Start*.
-- Người chơi tiến hành đặt tháp vào bản đồ để tháp tiêu diệt quân địch. 
-- Người chơi sẽ chiến thắng khi vượt qua cả 4 levels.
-- Người chơi sẽ thua cuộc nếu số mạng của người chơi giảm xuống 0.

## Coding

**Khi khởi động trò chơi:**
- Trong hàm main chính sẽ tạo ra một sân khấu (sence), một đối tượng GameController và một đối tượng GUIBuilder.
- Đối tượng GameController này sẽ load dữ liệu bản đồ và truyền vào trong đối tượng GameField.
	- Sau khi load bản đồ, đối tượng GameField sẽ sinh ra ngẫu nhiên các bụi cây, hòn sỏi,... vào chính nó (để trang trí :P)
- Đối tượng GUIBuilder sẽ khởi tạo các tính năng hiển thị trên sân khấu.
- Người chơi sẽ tương tác với sân khấu.

**Sau khi bấm Start:**
- Đối tượng GameController sẽ load dữ liệu về level tương ứng, trong đó bao gồm map và số lượng các loại quân địch.
- Đối tượng GameController sẽ quản lý vòng lặp của game, nó sẽ kiểm soát tất cả các sự kiện diễn ra như:
	- Thời gian cooldown của Tower
	- Kiểm tra va chạm giữa Bullet và Enemy, giảm máu Enemy và dọn dẹp các đối tượng đã biến mất hoặc chết
	- Kiểm soát sự thay đổi của điểm, tiền và máu của người chơi
	- Kiểm tra và dừng vòng lặp trong trường hợp thắng hoặc thua
	- ...
	
**Ưu điểm**
Game chạy ổn định, không bị giật.
 
**Nhược điểm**
Chưa có nhiều tính năng

**Các vấn đề khi gặp phải và hướng giải quyết đã sử dụng khi code game:**
- Máy bay đi vượt xe tăng thì làm cách nào để đảm bảo máy bay không bị xe tăng đè lên khi render?
	- Máy bay vượt xe tăng chứng tỏ máy bay được sinh ra sau xe tăng. Và trong list các entities thì các vật thể sẽ được thêm vào cuối list cho nên đảm bảo được thứ tự sinh ra theo thời gian trong list. Khi GameController render hình ảnh thì sẽ render từ đầu tới cuối list cho nên sẽ đảm bảo thứ tự là vật bị đè lên sẽ được render ra trước.
- Quân địch tự tìm đường như thế nào?
	- Quân địch tìm theo 4 hướng, hướng nào đi vào một Tile là Road thì nó sẽ chọn hướng đó và đi hết Tile đó. Sau khi đi hết Tile đó nó sẽ lặp lại thủ tục trên với điều kiện hướng được chọn tiếp theo không phải hướng cũ (không quay đầu lại).