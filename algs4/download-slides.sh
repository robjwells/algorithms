mkdir slides
cd slides
for url in $(http https://algs4.cs.princeton.edu/lectures/ | ack -h '"(\w+-2x2\.pdf)"' --output='https://algs4.cs.princeton.edu/lectures/$1')
http --download $url
