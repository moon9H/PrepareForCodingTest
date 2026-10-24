-- 코드를 입력하세요
SELECT ugu.USER_ID as USER_ID, ugu.NICKNAME as NICKNAME,
        concat(
            ugu.CITY, ' ', 
            ugu.STREET_ADDRESS1, ' ', 
            ugu.STREET_ADDRESS2
        ) as 전체주소, 
        concat(
            SUBSTRING(ugu.TLNO, 1, 3),
            "-",
            SUBSTRING(ugu.TLNO, 4, 4),
            "-",
            SUBSTRING(ugu.TLNO, 8, 4)
        ) as 전화번호
FROM USED_GOODS_USER ugu
JOIN USED_GOODS_BOARD ugb ON ugu.USER_ID = ugb.WRITER_ID
GROUP BY ugb.WRITER_ID
HAVING count(ugb.BOARD_ID) >= 3
ORDER BY ugu.USER_ID DESC;