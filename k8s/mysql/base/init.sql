configMapGenerator:
- name: sql-init-script
  files:
  - init.sql=init.sql  # 상대경로로 init.sql 지정