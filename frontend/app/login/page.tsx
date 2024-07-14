'use client'

import React, { FormEvent, useState } from 'react'

export default function LoginPage() {
  const [username, setUsername] = useState('')
  const [password, setPassword] = useState('')

  const handleLogin = (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault()

    console.log('username : ', username)
  }

  const handleSignup = () => {
    console.log('sign up page')
  }

  const handleInputChange =
    (setter: React.Dispatch<React.SetStateAction<string>>) => (e: React.ChangeEvent<HTMLInputElement>) => {
      console.log(e.target.value)
      setter(e.target.value)
    }

  return (
    <div className="flex flex-col items-center justify-center min-h-screen bg-gray-100">
      <h1 className="text-2xl font-bold mb-6">로그인</h1>
      <form onSubmit={handleLogin} className="bg-white p-8 rounded shadow-md w-80">
        <div className="mb-4">
          <span className="block text-gray-700">아이디</span>
          <input
            type="text"
            id="username"
            value={username}
            onChange={handleInputChange(setUsername)}
            required
            className="w-full px-3 py-2 border border-gray-300 rounded mt-1"
          />
        </div>
        <div className="mb-4">
          <span className="block text-gray-700">패스워드</span>
          <input
            type="password"
            id="password"
            value={password}
            onChange={handleInputChange(setPassword)}
            required
            className="w-full px-3 py-2 border border-gray-300 rounded mt-1"
          />
        </div>
        <button type="submit" className="w-full bg-blue-500 text-white py-2 rounded mt-4">
          확인
        </button>
      </form>
      <button onClick={handleSignup} className="mt-4 bg-gray-500 text-white py-2 px-4 rounded">
        회원가입
      </button>
    </div>
  )
}
