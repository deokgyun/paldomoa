import Link from 'next/link'
import React from 'react'

export default function Header() {
  return (
    <header className="w-full max-w-7xl mx-auto flex justify-between items-center py-4">
      <Link className="text-2xl font-bold" href="/">
        Logo
      </Link>
      <nav className="flex space-x-4">
        <Link href="/recruit" className="text-black hover:underline">
          팀원 모집
        </Link>
        <Link href="/match" className="text-black hover:underline">
          매칭
        </Link>
        <Link href="/market" className="text-black hover:underline">
          중고
        </Link>
        <Link href="/my" className="text-black hover:underline">
          팀관리
        </Link>
      </nav>
      <div className="flex space-x-2">
        <Link href="/login" className="px-4 py-2 rounded hover:bg-gray-100">
          <span className="text-black text-[14px] leading-[17px]">로그인</span>
        </Link>
        <Link href="/signin" className="px-4 py-2 bg-blue-500 rounded hover:bg-blue-700">
          <span className="text-white text-[14px] leading-[17px]">회원가입</span>
        </Link>
      </div>
    </header>
  )
}
