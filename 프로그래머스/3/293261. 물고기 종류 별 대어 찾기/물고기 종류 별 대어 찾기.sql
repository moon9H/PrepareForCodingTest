-- 코드를 작성해주세요
SELECT fi.id, fni.fish_name, fi.length
FROM fish_info fi
JOIN fish_name_info fni 
  ON fi.fish_type = fni.fish_type
WHERE (fi.fish_type, fi.length) IN (
    -- 물고기 종류별로 가장 큰 길이를 구하는 서브쿼리
    SELECT fish_type, MAX(length)
    FROM fish_info
    GROUP BY fish_type
)
ORDER BY fi.id;
