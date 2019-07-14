#!/usr/bin/env python3
import sys
import pathlib

template = '''\
# Ex {ex_string}


'''

if __name__ == '__main__':
    ex_dots = sys.argv[1]
    ex_underscores = ex_dots.replace(".", "_")
    ex_dir = pathlib.Path(__file__).resolve().parent.joinpath(
            'code', 'exercises'
    )
    ex_dir.joinpath(f'Ex{ex_underscores}.md').write_text(
            template.format(ex_string=ex_dots)
    )
